# 1
无重复字符的最长子串
https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/

思路：
问题的关键在于
一个子串判断了，不是重复，后续不需要再判断了

滑动窗口来优化


https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetcod/


# 2 回文子串
 - 是否是回文子串
  方法1：头尾指针遍历即可
  方法2：从中间到两边
  
  两者在这边没有区别，但是在
  
  1、判断一条单向链表是不是“回文”
  分析：对于单链表结构，可以用两个指针从两端或者中间遍历并判断对应字符是否相等。但这里的关键就是如何朝两个方向遍历。由于单链表是单向的，所以要向两个方向遍历的话，可以采取经典的快慢指针的方法，即先位到链表的中间位置，再将链表的后半逆置，最后用两个指针同时从链表头部和中间开始同时遍历并比较即可。
  2、判断一个栈是不是“回文”
  分析：对于栈的话，只需要将字符串全部压入栈，然后依次将各字符出栈，这样得到的就是原字符串的逆置串，分别和原字符串各个字符比较，就可以判断了。
https://wizardforcel.gitbooks.io/the-art-of-programming-by-july/content/01.04.html


- 最长回文子串
暴力解法就是没有利用已经判断是回文子串的情况

动态规划：
dp[i][j] 表示i-j字符串是否是回文子串

那么dp[i][j] = true
s[i-1] = s[j+1] 那么必然 dp[i-1][j+1]也是回文子串

初始化
https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/