# 中级实训测试报告

测试人员: 钟悦东 郑灿峰

## 1.配置Junit

为了简化配置，我们直接使用maven进行依赖引入，下面给出maven的关键依赖配置

```aidl
        ...
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-runner</artifactId>
            <version>1.2.0</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>5.2.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.8.1</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>compile</scope>
        </dependency>
        ...
```

## 2.编写测试模块

将测试全过程分为7个小点进行

1. 测试当前jumper在无障碍下的行动情况，在两格落点处有障碍的行动情况
2. 测试jumper两个落点为边界外的情况
3. 测试jumper正对面就是地图墙
4. 测试jumper 两格落点处有其他Actor
5. 测试jumper前面有其他Actor
6. 测试jumper前面有石头或者鲜花
7. 测试jumper有无落下鲜花

## 3.详细测试情况

### 测试1

```aidl
    @Test
    public void test1(){

        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(8, 5), alice);
        alice.setDirection(0);

        // if the cell in front of it is empty
        alice.act();
        assertEquals(new Location(6,5),alice.getLocation());

        // if the cell in front of it is rock
        world.add(new Location(4,5),new Rock());
        alice.act();
        assertEquals(new Location(5,5),alice.getLocation());
    }
```

jumper初始位置为(8,5)， 无障碍跳跃两格子应到(6,5)，

下次跳跃应到(4,5),但是(4,5)有石头，所以最后应该到(5,5)。

### 测试2

```aidl
    @Test
    public void test2(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(1, 1), alice);
        alice.setDirection(0);

        alice.act();
        assertEquals(new Location(0,1),alice.getLocation());
    }
```

将jumper 置于边界附近，下次跳跃应到(-1,1)，由于越界，最终应该到(0,1)。

### 测试3

```aidl
    @Test
    public void test3(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(0, 1), alice);
        alice.setDirection(0);

        alice.act();
        assertEquals(new Location(0,1),alice.getLocation());
        assertEquals(45,alice.getDirection());
    }
```

将jumper至于边界，下次跳跃受到边界阻碍，也无法移动，应该直接转向45度。

#### 测试4

```aidl
    @Test
    public void test4() {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(8, 5), alice);
        alice.setDirection(0);
        Jumper other = new Jumper();
        world.add(new Location(6,5),other);

        alice.act();
        assertEquals(new Location(7,5),alice.getLocation());

    }
```



将其他Actor置于其两格前，jumper直接move.

#### 测试5

```aidl
    @Test
    public void test5(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(8, 5), alice);
        alice.setDirection(0);
        Jumper other = new Jumper();
        world.add(new Location(7,5),other);

        alice.act();
        assertEquals(new Location(6,5),alice.getLocation());
    }
```

#### 测试6

```aidl
    @Test
    public void test6(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(8, 5), alice);
        alice.setDirection(0);
        //if that is rock
        Rock other = new Rock();
        world.add(new Location(7,5),other);
        alice.act();
        assertEquals(new Location(6,5),alice.getLocation());
        //if that is flower
        Flower f = new Flower();
        world.add(new Location(5,5),f);
        alice.act();
        assertEquals(new Location(4,5),alice.getLocation());
    }
```

将石头或者鲜花放置前面,其应直接jump

#### 测试7

```aidl
    //other: jumper should not leave the flower
    @Test
    public void test7(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Location location = new Location(8,5);
        world.add(location, alice);
        alice.setDirection(0);

        Grid<Actor> g = world.getGrid();
        alice.act();
        assert(! (g.get(location) instanceof Flower));
    }
```

Jumper jump 后不应该留下鲜花.
