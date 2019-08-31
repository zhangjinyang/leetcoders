package com.leetcoders.array;

/**
 * LeetCode 4
 * Created by Zhang.Jinyang&Hardy on 2019-08-31.
 */
public class MedianInTwoSortedArrays {

  /**
   * todo 在k==1的情况处理的还是有点啰嗦，待完善
   * O(log(m+n))
   */
  static double findMedian(int[] nums1, int[] nums2) {

    if (nums1.length == 0 && nums2.length == 0) {
      return 0;
    }

    int flag = (nums1.length + nums2.length) % 2;
    int k = (nums1.length + nums2.length + 1) / 2;

    return flag == 0 ?
        (findSingleNum(k, nums1.length, nums2.length, -1, -1, nums1, nums2, 0)
            + findSingleNum(k, nums1.length, nums2.length, -1, -1, nums1, nums2, 1))*0.5
        : findSingleNum(k, nums1.length, nums2.length, -1, -1, nums1, nums2, 0);

  }

  //core algorithm
  static int findSingleNum(int k, int len1, int len2, int startI1, int startI2, int[] nums1,
      int[] nums2, int offset) {

    //使nums1总是较小的那个数组
    if (len2 < len1) {
      return findSingleNum(k, len2, len1, startI2, startI1, nums2, nums1, offset);
    }

    if (startI1 == (len1 - 1)) {//num1已为空,k值需要重新计算，因为由于num1太小，可能排除掉的数比较少
      k = (len2 + len1 + 1) / 2 - (startI1 + 1 + startI2 + 1);
      return nums2[startI2 + k + offset];
    }

    if (k == 1) {
      if (offset == 0) {
        return Math.min(nums1[startI1 + 1], nums2[startI2 + 1]);
      } else {
        System.out.println("startI1=" + startI1 + ", startI2=" + startI2);
        if (nums1[startI1 + 1] > nums2[startI2 + 1]) {

          return startI2 + 2 >= len2 ? nums1[startI1 + 1]
              : Math.min(nums1[startI1 + 1], nums2[startI2 + 2]);
        } else if(nums1[startI1 + 1] == nums2[startI2 + 1]){
          return nums1[startI1 + 1];
        } else {
          return startI1 + 2 >= len1 ? nums2[startI2 + 1]
              : Math.min(nums2[startI2 + 1], nums1[startI1 + 2]);
        }
      }
    }
    int i1 = Math.min(len1 - 1, startI1 + (k / 2));
    int i2 = Math.min(len2 - 1, startI2 + (k / 2));

    k = k - (k / 2);
    if (nums1[i1] > nums2[i2]) {
      return findSingleNum(k, len1, len2, startI1, i2, nums1, nums2, offset);
    } else {
      return findSingleNum(k, len1, len2, i1, startI2, nums1, nums2, offset);
    }


  }

}
