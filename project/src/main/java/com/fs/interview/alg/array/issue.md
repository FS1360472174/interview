数组问题有什么共性呢？

# 数组+双指针类解法

https://leetcode-cn.com/problems/3sum/
https://leetcode-cn.com/problems/3sum-closest/
https://leetcode-cn.com/problems/4sum/

主干思路：
数组求和先排序，接下来通过双指针移位即可。和求数组矩阵面积之和类似。

for (int i = 0; i < nums.length; i++) {
    int cur = nums[i];
    int left = i + 1;
    int right = nums.length - 1;
    while (left < right) {
        int tmp = cur + nums[left] + nums[right];
        if (tmp == 0) {
            res.add(Arrays.asList(cur, nums[left], nums[right]));
            // 一组匹配了，还需要匹配其他组
            left++;
            right--;
        } else if (tmp < 0) {
            // 左指针，增加三数之和
            left++;
        } else {
            // 右指针，减少三数之和
            right--;
        }
    }
}

然后边界条件：

必须要处理的重复数情况
遍历数组的数据有可能重复，就num[i] == num[i-1]
找到3个数相等了，这时候左指针，右指针接下来数都可以重复
可选的重复数判断
当然移动左右指针时，数也可能重复，没必要再走一遍求和判断了，可以移除

移动左指针时，左指针的下一个数可能重复
移动右指针时，右指针的下个树可能重复

作者：FS1360472174
链接：https://leetcode-cn.com/problems/3sum/solution/shu-zu-de-nge-shu-zhi-he-jie-ti-si-lu-by-fs1360472/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
