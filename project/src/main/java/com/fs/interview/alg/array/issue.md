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

合并4个数思路转换为合并3个数解法。

注意点:
只适应于正数
```
 // 排序后第一个数最小，不能大于sum = 0
            if (target > 0 && nums[i] > target) {
                return res;
            }
```

另外第一次提交只击败了15%，把一些边界条件加上，就能击败63%

```
  if (i+3 < nums.length) {
                int min1 = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
                if (min1 > target) {
                    break;
                }
                int max1 = nums[i] + nums[length - 1] + nums[length - 2] + nums[length - 3];
                if (max1 < target) {
                    continue;
                }
            }

```
如果在 threeSum在加上，时间应该会更快
