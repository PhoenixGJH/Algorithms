package com.example.phoenix.algorithms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 一些常见的排序
 *
 * @author Phoenix
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GJH";
    private int tobeSort[] = {2, 3, 6, 1, 8, 5, 4, 9, 7, 10};
    private int length = tobeSort.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
    }

    /**
     * 冒泡排序
     * 通过与相邻元素的比较和交换来把小的数交换到最前面
     * 时间复杂度O(n^2)
     */
    private void bubbleSort() {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (tobeSort[j] > tobeSort[j + 1]) {
                    int temp = tobeSort[j];
                    tobeSort[j] = tobeSort[j + 1];
                    tobeSort[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 直接选择排序
     * 选择排序的思想其实和冒泡排序有点类似，都是在一次排序后把最小的元素放到最前面。
     * 但是过程不同，冒泡排序是通过相邻的比较和交换。而选择排序是通过对整体的选择。
     * 选择排序可以看成冒泡排序的优化，因为其目的相同，只是选择排序只有在确定了最小数的前提下才进行交换，大大减少了交换的次数。
     * 选择排序的时间复杂度为O(n^2)。
     */
    private void sort_1() {
        //N个数组元素，就需要循环N-1轮
        for (int i = 0; i < length - 1; i++) {
            //最小数的索引，该索引每次都根据外层循环的计数器来觉得初始值。
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                //根据最小数的索引，判断当前这个数是否小于最小数。
                //如果小于，则把当前数的索引作为最小数的索引。
                //否则不处理。
                if (tobeSort[minIndex] > tobeSort[j]) {
                    minIndex = j;
                }
                //直到循环完成的时候，minIndex肯定就是当前这轮循环中，最小的那个。
            }
            //得到最小数的索引后，把该索引对应的值放到最左边，并且把最左边的值放到索引所在的位置.
            //最左边的值
            int temp = tobeSort[i];
            //把最小数索引对应的值放到最左边
            tobeSort[i] = tobeSort[minIndex];
            //把原来最左边对应的值放到最小数索引所在的位置
            tobeSort[minIndex] = temp;
        }
    }

    /**
     * 插入排序
     * <p>
     * 插入排序不是通过交换位置而是通过比较找到合适的位置插入元素来达到排序的目的的。
     * 对5,3,8,6,4这个无序序列进行简单插入排序，首先假设第一个数的位置时正确的，然后3要插到5前面，把5后移一位，
     * 变成3,5,8,6,4.然后8不用动，6插在8前面，8后移一位，4插在5前面，从5开始都向后移一位。
     * 注意在插入一个数的时候要保证这个数前面的数已经有序。
     * 简单插入排序的时间复杂度也是O(n^2)。
     */
    private void insertSort() {
        for (int i = 0; i < length - 2; i++) {
            if (tobeSort[i] > tobeSort[i + 1]) {
                int temp = tobeSort[i + 1];
                int j = i;
                tobeSort[i + 1] = tobeSort[i];
                while (j > 0 && tobeSort[j - 1] > temp) {
                    tobeSort[j] = tobeSort[j - 1];
                    j--;
                }
                tobeSort[j] = temp;
            }
        }
    }

    /**
     * 快速排序
     * <p>
     * 在实际应用当中快速排序确实也是表现最好的排序算法。其思想是来自冒泡排序，冒泡排序是通过相邻元素的比较和交换把最小的冒泡到最顶端，
     * 而快速排序是比较和交换小数和大数，这样一来不仅把小数冒泡到上面同时也把大数沉到下面。
     * 举个栗子：对5,3,8,6,4这个无序序列进行快速排序，思路是右指针找比基准数小的，左指针找比基准数大的，交换之。
     * 5,3,8,6,4 用5作为比较的基准，最终会把5小的移动到5的左边，比5大的移动到5的右边。
     * 5,3,8,6,4 首先设置i,j两个指针分别指向两端，j指针先扫描（思考一下为什么？）4比5小停止。然后i扫描，8比5大停止。交换i,j位置。
     * 5,3,4,6,8 然后j指针再扫描，这时j扫描4时两指针相遇。停止。然后交换4和基准数。
     * 4,3,5,6,8 一次划分后达到了左边比5小，右边比5大的目的。之后对左右子序列递归排序，最终得到有序序列。
     * 为什么一定要j指针先动呢？首先这也不是绝对的，这取决于基准数的位置，因为在最后两个指针相遇的时候，要交换基准数到相遇的位置。
     * 一般选取第一个数作为基准数，那么就是在左边，所以最后相遇的数要和基准数交换，那么相遇的数一定要比基准数小。所以j指针先移动才能先找到比基准数小的数。
     * <p>
     * 快速排序是不稳定的，其时间平均时间复杂度是O(nlgn)。
     */
    private void quickSort(int left, int right) {
        int i = left;
        int j = right;

        int standard = tobeSort[left];//基准数
        while (left != right) {
            //从右边找比基准数小的数字
            while (tobeSort[right] >= standard && left < right) {
                right--;
            }
            //从左边找比基准数大的数字
            while (tobeSort[left] <= standard && left < right) {
                left++;
            }
            if (left < right) {
                int temp = tobeSort[right];
                tobeSort[right] = tobeSort[left];
                tobeSort[left] = temp;
            }
        }

        //基准数归位
        tobeSort[i] = tobeSort[left];
        tobeSort[left] = standard;
        //递归
        if (i < (left - 1))
            quickSort(i, left - 1);//左边排序
        if ((left + 1) <= j)
            quickSort(left + 1, j);//右边排序
    }

    /**
     * 堆排序
     */
    private void heapSort() {
        for (int i = 0; i < length; i++) {
            createMaxHeap(tobeSort, length - 1 - i);
            if (0 != length - 1 - i) {
                tobeSort[0] = tobeSort[0] + tobeSort[length - 1 - i];
                tobeSort[length - 1 - i] = tobeSort[0] - tobeSort[length - 1 - i];
                tobeSort[0] = tobeSort[0] - tobeSort[length - 1 - i];
            }
        }
    }

    private void createMaxHeap(int[] array, int lastIndex) {
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //保存当前正在判断的节点
            int k = i;
            //若当前节点的子节点存在
            while (2 * k + 1 <= lastIndex) {
                //biggerIndex总是记录较大的节点的值，先赋值为当前判断节点的左子节点
                int biggerIndex = 2 * k + 1;
                if (biggerIndex < lastIndex) {
                    // 若右子节点存在，否则此时biggerIndex应该等于 lastIndex
                    if (array[biggerIndex] < array[biggerIndex + 1]) {
                        // 若右子节点值比左子节点值大，则biggerIndex记录的是右子节点的值
                        biggerIndex++;
                    }
                }
                if (array[k] < array[biggerIndex]) {
                    // 若当前节点值比子节点最大值小，则交换2者得值，交换后将biggerIndex值赋值给k
                    if (0 != length - 1 - i) {
                        array[k] = array[k] + array[biggerIndex];
                        array[biggerIndex] = array[k] - array[biggerIndex];
                        array[k] = array[k] - array[biggerIndex];
                    }
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 希尔排序(缩小增量排序)
     * <p>
     * 希尔排序是插入排序的一种高效率的实现，也叫缩小增量排序。简单的插入排序中，如果待排序列是正序时，时间复杂度是O(n)，
     * 如果序列是基本有序的，使用直接插入排序效率就非常高。希尔排序就利用了这个特点。
     * 基本思想是：先将整个待排记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录基本有序时再对全体记录进行一次直接插入排序。
     * <p>
     * 时间复杂度可以达到O(n^1.3)
     */
    private void shellSort() {
        int d = length / 2;
        while (d >= 1) {


//            for (int i = 0; i < length; i++) {
//                for (int j = i; j < length - d; j += d) {
//                    if (tobeSort[j] > tobeSort[j + d]) {
//                        int tmp = tobeSort[j];
//                        tobeSort[j] = tobeSort[j + d];
//                        tobeSort[j + d] = tmp;
//                    }
//                }
//            }


            for (int i = 0; i < d; i++) {
                //插入排序
                for (int j = i + d; j < length - 2; j += d) {
                    if (tobeSort[j] > tobeSort[j + 1]) {
                        int temp = tobeSort[j + 1];
                        int k = j;
                        tobeSort[j + 1] = tobeSort[j];
                        while (k > 0 && tobeSort[k - 1] > temp) {
                            tobeSort[k] = tobeSort[k - 1];
                            k--;
                        }
                        tobeSort[k] = temp;
                    }
                }
            }


            d /= 2;
        }
    }

    /**
     * 将有二个有序数列a[first...mid]和a[mid...last]合并。
     *
     * @param a
     * @param first
     * @param mid
     * @param last
     * @param temp
     */
    void mergearray(int a[], int first, int mid, int last, int temp[]) {
        int i = first, j = mid + 1;
        int m = mid, n = last;
        int k = 0;

        while (i <= m && j <= n) {
            if (a[i] <= a[j])
                temp[k++] = a[i++];
            else
                temp[k++] = a[j++];
        }

        while (i <= m)
            temp[k++] = a[i++];

        while (j <= n)
            temp[k++] = a[j++];

        for (i = 0; i < k; i++)
            a[first + i] = temp[i];
    }

    void mergesort(int a[], int first, int last, int temp[]) {
        if (first < last) {
            int mid = (first + last) / 2;
            mergesort(a, first, mid, temp);    //左边有序
            mergesort(a, mid + 1, last, temp); //右边有序
            mergearray(a, first, mid, last, temp); //再将二个有序数列合并
        }
    }

    boolean MergeSort(int a[], int n) {
        int[] tmp = new int[n];
        mergesort(a, 0, n - 1, tmp);
        return true;
    }

    /**
     * 桶排序
     */
    private void bucketSortd() {
        int a[] = new int[10];
        for (int i = 0; i < length; i++) {
            a[i] = 0;
        }
        for (int i = 0; i < length; i++) {
            a[tobeSort[i] - 1]++;
        }

        int aLength = a.length;
        for (int i = 0; i < aLength; i++) {
            if (a[i] > 0)
                Log.d(TAG, String.valueOf(i + 1));
        }
    }

    @Override
    public void onClick(View v) {
        StringBuilder builder = new StringBuilder("Before:");
        for (int tmp : tobeSort) {
            builder.append(tmp).append(" ");
        }
        Log.d(TAG, builder.toString());
        switch (v.getId()) {
            case R.id.button1://冒泡
                bubbleSort();
                break;
            case R.id.button2://快速
                quickSort(0, length - 1);
                break;
            case R.id.button3://插入排序
                insertSort();
                break;
            case R.id.button4://桶排序
                Log.d(TAG, "After");
                bucketSortd();
                break;
            case R.id.button5://希尔排序
                shellSort();
                break;
            case R.id.button6://直接选择排序
                sort_1();
                break;
            case R.id.button7://堆排序
                heapSort();
                break;
            case R.id.button8://归并排序
                MergeSort(tobeSort, length);
                break;
        }
        if (v.getId() != R.id.button4) {
            StringBuilder builder1 = new StringBuilder("After:");
            for (int tmp : tobeSort) {
                builder1.append(tmp).append(" ");
            }
            Log.d(TAG, builder1.toString());
        }
    }
}
