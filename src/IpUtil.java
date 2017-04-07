/**ipToInt：实现IP与整数的相互转换

        接口原型：

public class IpUtil {
    public static int ipToInt(String ip);
    public static String ipToStr(int ip);
}


要求：


        不能使用Long.parseLong(), Integer.parseInt(), String.split()等帮助函数
        注意代码优雅性
        实现要求空间复杂度和时间复杂度尽可能高效
        给出测试用例
**/


/**
 * Created by Zhou on 2017/4/6.
 */
public class IpUtil {
    /**
     * 输入正确的ip 返回int数值 32位来分四段存储数据
     * @param ip
     * @return
     */
    public static int ipToInt(String ip){
        char[] ipCharArray=ip.toCharArray();
        int ipCharArrayLength=ipCharArray.length;
        int[] intIpArray=new int[4];
        int intIpArrayIndex=0;
        int startIndex=0;
        for(int i=0;i<ipCharArrayLength;i++){
            if(ipCharArray[i]=='.'){
                intIpArray[intIpArrayIndex]=getIntFromCharArray(ipCharArray,startIndex,i-1);
                intIpArrayIndex++;
                startIndex=i+1;//进入下一段
            }
        }
        if(intIpArrayIndex!=3){
            return -1;//小数点不是3个
        }
        intIpArray[intIpArrayIndex]=getIntFromCharArray(ipCharArray,startIndex,ipCharArrayLength-1);

        return (intIpArray[0]<<24 )+ (intIpArray[1]<<16 )+ (intIpArray[2]<<8) + (intIpArray[3]);
    }

    /**
     * 输入int值 返回标准的ip地址
     * @param ip
     * @return
     */
    public static String ipToStr(int ip){
        //高位补0 如 ip>>24&0xFF 获取到高8位 以此类推
        return ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "."
                + ((ip >> 8) & 0xFF) + "." + (ip & 0xFF);
    }

    /**
     * 从ip字符数组中 抽取int数值
     * 比如 ['2''2''5''.''2'‘5’‘5’]   getIntFromCharArray(array,0,3) 返回 225
     * @param ipChars 输入数组
     * @param start 起始位置（包括）
     * @param end 结束位置（包括）
     * @return
     */
    public static int getIntFromCharArray(char[] ipChars,int start,int end){
        int resultValue=0;
        for(int j=start;j<end+1;j++){
            resultValue*=10;
            resultValue+=(ipChars[j]-'0');//int值 ascii相减
        }
        return resultValue;
    }

    public static void main(String[] args) {

        int num = ipToInt("192.168.100.203");
        System.out.println(ipToStr(num));

        int num2 = ipToInt("192.168.1.1");
        System.out.println(ipToStr(num2));

        int num3 = ipToInt("256.168.1.1");//不正确的ip地址
        System.out.println(ipToStr(num3));

    }
}
