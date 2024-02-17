#### 1. 集群搭建（不同服务器）

- 版本：zookeeper.3.4.14
- 依赖：java环境、rsync命令（yum -y install rsync）
##### 1.1 hosts配置

这里使用三台机器，分别在每台机器的/etc/hosts中添加：

```
192.168.199.128        zookeeper1
192.168.199.129        zookeeper2
192.168.199.130        zookeeper3
```

##### 1.2 免密登录

- 这一步骤可以省略，但是复制时就要输入密码了，不方便后续维护，因而配置免密登录，在各个节点分别执行：

```
ssh-keygen -t rsa  //一直回车即可
ssh-copy-id zookeeper1
ssh-copy-id zookeeper2
ssh-copy-id zookeeper3
```

- 测试免密登录是否配置成功，在各个节点分别执行：

```
ssh zookeeper1
ssh zookeeper2
ssh zookeeper3
```

##### 1.3 下载并解压

到官网下载并上传到linux（zookeeper1），这里放在了根目录

```
tar -zxvf ~/zookeeper-3.4.14.tar.gz -C /usr/local/
```

##### 1.4 配置

1）配置备份

```
cp /usr/local/zookeeper-3.4.14/conf/zoo_sample.cfg /usr/local/zookeeper-3.4.14/conf/zoo.cfg		备份，作为配置使用
```

2）文件配置

- vim /usr/local/zookeeper-3.4.14/conf/zoo.cfg

```
dataDir=/usr/local/zookeeper-3.4.14/data		存放数据的目录

#集群配置，这里的1、2、3需要在dataDir目录下创建myid，不同机器的值，分别指定为1、2、3，
#2888为当前服务器与leader服务器交换信息的端口
#3888为选举时，各个服务器之间的通信端口
server.1=zookeeper1:2888:3888
server.2=zookeeper2:2888:3888
server.3=zookeeper3:2888:3888
```

- myid文件创建

```
mkdir /usr/local/zookeeper-3.4.14/data		data目录创建
vi /usr/local/zookeeper-3.4.14/data/myid		创建myid，指定为1
```

3）把zk根目录配置到PATH

```
vi /etc/profile		在最下面添加

export ZK_HOME=/usr/local/zookeeper-3.4.14
export PATH=$PATH:$ZK_HOME/bin
```

4）将上面修改好的配置，同步到zookeeper2、zookeeper3，并修改myid分别为2, 3，对应3.1.4的第2点中的server.[myid]

```
rsync -av /usr/local/zookeeper-3.4.14 zookeeper2:/usr/local/
rsync -av /usr/local/zookeeper-3.4.14 zookeeper3:/usr/local/
rsync -av /etc/profile zookeeper2:/etc/
rsync -av /etc/profile zookeeper3:/etc/
```

5）在每台机器分别重新加载/etc/profile使其生效

```
source /etc/profile
```

6）每台机器均开启2181/tcp 2888/tcp 3888/tcp这些端口

```
firewall-cmd --zone=public --add-port=2181/tcp -permanent
firewall-cmd --zone=public --add-port=2888/tcp --permanent
firewall-cmd --zone=public --add-port=3888/tcp --permanent
firewall-cmd --reload
firewall-cmd --zone=public --list-ports      //查看开启的端口
```

##### 1.5 相关脚本命令

```
zkServer.sh [params]		开启、关闭、查看状态，params可选值：start/stop/status
zkCli.sh		连上zk服务端

启动集群的话，分别在每台机器上启动zk服务端既可，可以通过查看状态来查看每台机器对应的角色：一个leader，其余为follower
```

#### 2. 集群搭建（docker）

##### 2.1 拉取指定版本zookeeper

```
docker pull zookeeper:3.4.14
```

##### 2.2 创建集群网络

```
docker network create --driver bridge --gateway 172.30.0.1 --subnet 172.30.0.0/16 zk-cluster
```

##### 2.3 创建并启动容器

```
docker run -itd -p 2181:2181 --name zk1 --net zk-cluster --ip 172.30.0.11 -v /opt/docker/zk1/conf:/conf -v /opt/docker/zk1/data:/data zookeeper:3.4.14
docker run -itd -p 2182:2181 --name zk2 --net zk-cluster --ip 172.30.0.12 -v /opt/docker/zk2/conf:/conf -v /opt/docker/zk2/data:/data zookeeper:3.4.14
docker run -itd -p 2183:2181 --name zk3 --net zk-cluster --ip 172.30.0.13 -v /opt/docker/zk3/conf:/conf -v /opt/docker/zk3/data:/data zookeeper:3.4.14
```

##### 2.4 修改zoo.cfg

分别修改/opt/docker/zk1/conf/zoo.cfg、/opt/docker/zk2/conf/zoo.cfg、/opt/docker/zk2/conf/zoo.cfg，增加如下配置：

```
dataDir=/data
server.1=172.30.0.11:2888:3888
server.2=172.30.0.12:2888:3888
server.3=172.30.0.13:2888:3888
```

##### 2.5 修改myid

分别修改/opt/docker/zk1/data/myid、/opt/docker/zk2/data/myid、/opt/docker/zk3/data/myid下内容为1、2、3

##### 2.6 开启端口

```
firewall-cmd --zone=public --add-port=2181/tcp --add-port=2182/tcp --add-port=2183/tcp -permanent && firewall-cmd --reload && firewall-cmd --list-ports
```

##### 2.7 重启三个容器

```
docker restart zk1
docker restart zk2
docker restart zk3
```

##### 2.8 查看容器状态

```
docker exec -it zk1 ./bin/zkServer.sh status && docker exec -it zk2 ./bin/zkServer.sh status && docker exec -it zk3 ./bin/zkServer.sh status
```
