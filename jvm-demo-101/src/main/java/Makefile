test01:
	javac AppMain.java
	java -Xms1G -Xmx1G -XX:NativeMemoryTracking=detail  -XX:MetaspaceSize=40m -XX:MaxMetaspaceSize=40m -XX:+UseConcMarkSweepGC -cp . AppMain
test01_nmt:
	jcmd $(shell sh -c "jps | grep AppMain | cut -d ' ' -f 1") VM.native_memory detail

disruptor_test:
	javac -cp ./disruptor-3.4.4.jar:.  DistruptorTest.java
	java -cp disruptor-3.4.4.jar:. DistruptorTest

hello:
	javac Hello.java
	java -Xms1G -Xmx1G Hello

hello_pretouch:
	javac Hello.java
	java -Xms1G -Xmx1G -XX:+AlwaysPreTouch Hello
mem_test_01:
	javac MemTest01.java
	java -Xms2G -Xmx2G -Xmn1G -XX:NativeMemoryTracking=detail -XX:+PrintGCDetails  -XX:+UseConcMarkSweepGC MemTest01
mem_test_03:
	javac MemTest03.java
	java -Xms2G -Xmx2G -Xmn1G -XX:NativeMemoryTracking=detail -XX:+PrintGCDetails  -XX:+UseConcMarkSweepGC MemTest03

mem_test_02:
	javac MemTest02.java
	java -Xms8G -Xmx8G -Xmn6G -XX:NativeMemoryTracking=detail -XX:+PrintGCDetails  -XX:+UseConcMarkSweepGC MemTest02

off_heap_test:
	javac OffHeapTest.java
	java -Xms4G -Xmx4G -Xmn2000m -XX:NativeMemoryTracking=detail -XX:+PrintGCDetails  -XX:+UseConcMarkSweepGC OffHeapTest
off_heap_test_2:
	javac OffHeapTest2.java
	java -Xms4G -Xmx4G -Xmn2000m -XX:NativeMemoryTracking=detail -XX:+PrintGCDetails  -XX:+UseConcMarkSweepGC OffHeapTest2

off_heap_test_3:
	javac OffHeapTest3.java
	#java -Xms1G -Xmx1G -Xmn800m -XX:MaxDirectMemorySize=2G  -XX:NativeMemoryTracking=detail -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC OffHeapTest3
	java -Xms1G -Xmx1G -Xmn800m -XX:MaxDirectMemorySize=2G  -XX:NativeMemoryTracking=detail -XX:+PrintGCDetails  -XX:+UseG1GC OffHeapTest3

