import java.util.Arrays;

/**
 TopN：求给定的N个有序数组里最大的K个数

 示例：


 输入：[ [1, 2, 3], [4, 5, 6], [7, 8, 9] ]，求Top5
 期望输出：[9, 8, 7, 6, 5]
 注：每个数组是从小到大


 要求：


 注意代码优雅性
 实现要求空间复杂度和时间复杂度尽可能高效
 给出测试用例


 接口原型，供参考：

 long[] topn(long[][] array, int k)

 **/

/**
 * Created by Zhou on 2017/4/6.
 */
public class TopN {
    public static long[] topn(long[][] array,int k){
        long[] tempArray;
        long[] resultArray=new long[k];

        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-i-1;j++) {//每一次循环把拥有最大值的数组排到无序数组部分的最后
                //冒泡排序 按数组的最大值从小大排序
                if(getLastOne(array[j])>getLastOne(array[j+1])){
                    tempArray = array[j];
                    array[j]=array[j+1];
                    array[j+1]=tempArray;
                }
            }

        }
        //至此 arry中的数组是按最大值从小到大排序的

        int tempArrayIndex;
        for(int i=array.length-1;i>=0;i--){
            tempArray=array[i];
            tempArrayIndex=tempArray.length-1;//指向最后一个
            boolean isExchange=false;
            for(int j=0;j<k;j++){
                if(resultArray[j]<tempArray[tempArrayIndex]){
//                    resultArray[j]=tempArray[tempArrayIndex];直接替换会丢失数据 要用插入
                    insertValue(resultArray,j,tempArray[tempArrayIndex]);//插入结果数组
                    tempArrayIndex--;
                    isExchange=true;
                }
                if(tempArrayIndex<0){
                    break;//数组个数小于k的情况
                }
            }
            //如果一轮下来都没有交换 说明resultArray里的数组最小值 大于或者等于剩下的数组的最大值
            if(!isExchange){
                return resultArray;
            }
        }
        //如果总的数字个数小于k 用0(数组初始化的0)补充，
        return resultArray;
    }

    /**
     * 获取数组的最后一个值
     * @param array 输入数组
     * @return
     */
    public static long getLastOne(long[] array){
        return array[array.length-1];
    }

    /**
     * 在一个从大到小的数组中，插入一个值，将最小值撤出数组，同时保持数组有序。
     * @param array 输入数组
     * @param position 插入位置
     * @param value 插入数值
     */
    public static void insertValue(long[] array,int position,long value){
        for(int i=array.length-1;i>position;i--){
                array[i]=array[i-1];
        }
        array[position]=value;
    }

    public static void main(String[] args) {
        long[][] testArray = {{4, 5, 6}, {1, 2, 3,4,5}, {3, 4, 5}};
        long[] result = topn(testArray, 5);
        long[] expect={6,5,5,5,4};
        System.out.println(Arrays.equals(result,expect));


        long[][] testArray2 = {{1,2, 9}, {3, 4,5,5,6}, {3, 4, 5}};
        long[] result2 = topn(testArray2, 4);
        long[] expect2={9,6,5,5};
        System.out.println(Arrays.equals(result2,expect2));

        long[][] testArray3 = {{1, 9}, {3,6}, {3}};
        long[] result3 = topn(testArray3, 6);
        long[] expect3={9,6,3,3,1,0};//考虑数据不足的情况 1不能丢失 同时补充0
        System.out.println(Arrays.equals(result3,expect3));
    }
}
