
### bug记录
1. 直接使用netty传输文件，失败！
![大文件传输失败](images/image-tarnsport-failed.png)
尝试将文件内容减少
2. 尝试设置VM options 将-Dio.netty.buffer.checkBounds=false，但是发现
   解码器的decode函数的ByteBuf默认为2048，失败！
