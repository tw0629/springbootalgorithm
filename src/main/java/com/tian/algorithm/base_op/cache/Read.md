LRU （Least Recently Used）缓存机制（看时间）
LFU （Least Frequently Used）缓存机制（看访问次数）


LRU算法：



LFU算法：  
        1 TreeSet （哈希表 + 平衡二叉树）
        2 PriorityQueue （哈希表 + 优先队列）
        3 List：若用list,得每次遍历得到最小的node
        4 HashMap （ 双哈希表 即两个HashMap） O(1)
            (一个用来存放node的key-value,一个用来存放 频率-相同频率node的List)

https://leetcode-cn.com/problems/lfu-cache/solution/lfuhuan-cun-by-leetcode-solution/

     对于缓存的每一项构建一个Node来存储，其中包含属性key  value  count  time
    
     最近最不经常使用的元素:
          count表示该Node节点被使用的次数
          time表示该缓存创建以来经过的时间
          即最近最不经常使用的元素即为count最小,time最小的元素，
          即需要对于一系列Node节点进行count time的二重排序
     https://blog.csdn.net/u014106644/article/details/105338273