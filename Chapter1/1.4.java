import java.util.*;
class Test{

    public static void main(String[] args) {  
    /**
        ## 基础问题：在节假日的时候，书店一般都会做促销活动。由于《哈利波特》系列相当畅销，店长决定通过促销活动来回馈读者。上柜的《哈利波特》平装本系列中，一共有五卷。假设每一卷单独销售均需8欧元。如果读者一次购买不同的两卷，就可以扣除5%的费用，三卷则更多。假设具体折扣的情况如下
|本数|折扣|
|---|---|
|2|5%|
|3|10%|
|4|20%|
|5|25%|
在一份订单中，根据购买的卷数及本数，就会出现可以应用不同折扣规则的情况。但是，一本书只会应用一个折扣规则。比如，读者一共买了两本卷一，一本卷二。那么，可以享受到5%的折扣。另外一本卷一则不能享受折扣。如果有多种折扣，希望计算出的总额尽可能的低。要求根据以上需求，设计出算法，能够计算出读者所购买的一批书的最低价格。

### 证明普通的贪心法不行
举个例子， 当输入的数据为(2,2,2,1,1)的时候，如果按照享受最高折扣计算，那么对应的折扣策略就会拆分变成(1,1,1,1,1)和(1,1,1,0,0)两种，总价格变为8×0.75×5＋8×0.9×3＝ 51.6欧元。但是如果我们变一下策略，选择4+4，购买序列变为(1,1,1,1,0)以及(1,1,1,0,1)，那么总共花费8×0.8×5＋8×0.8×5＝51.2欧元。

### 使用动态规划解题
状态定义
$X_i$代表购买第$i$本书的数量，
$F(X_1,X_2,X_3,X_4,X_5)$表示购买这些书的最小花费
$F(Y_1,Y_2,Y_3,Y_4,Y_5)$满足$Y_1 >= Y_2 >= Y_3 >= Y_4 >= Y_5$

$$F(Y_1,Y_2,Y_3,Y_4,Y_5) = 
\left\{
\begin{array}{lcl}
0 &      & (Y_1 = Y_2 = Y_3 = Y_4 = Y_5 = 0) \\
5*8*(1-25\%) + F(Y_1-1,Y_2-1,Y_3-1,Y_4-1,Y_5-1) &      &  (Y_5 > 0) \\
4*8*(1-20\%) + F(Y_1-1,Y_2-1,Y_3-1,Y_4-1,Y_5) &      &  (Y_4 > 0) \\
3*8*(1-10\%) + F(Y_1-1,Y_2-1,Y_3-1,Y_4,Y_5) &      &  (Y_3 > 0) \\
2*8*(1-5\%) + F(Y_1-1,Y_2-1,Y_3,Y_4,Y_5) &      &  (Y_2 > 0) \\
1*8 + F(Y_1-1,Y_2,Y_3,Y_4,Y_5)&      &  (Y_1 > 0)  
\end{array} \right.$$

动态规划完毕之后，需要调整$Y_1,Y_2,Y_3,Y_4,Y_5$ 使其满足$Y_1 >= Y_2 >= Y_3 >= Y_4 >= Y_5$，然后才能进行下一次的动态规划过程。

        */
        int singlePrice=8;
        double[] discounts={1,0.95,0.9,0.8,0.75};
        int[] nums={2,2,2,1,1};
        Arrays.sort(nums);
        int max=nums[4];
        double[][][][][] dp=new double[max+1][max+1][max+1][max+1][max+1];
        for(int i=1;i<=max;i++){
            for(int j=0;j<=i;j++){
                for(int k=0;k<=j;k++){
                    for(int l=0;l<=k;l++){
                        for(int m=0;m<=l;m++){
                            if(m>=1){
                                if(dp[i][j][k][l][m]==0 || dp[i][j][k][l][m]>dp[i-1][j-1][k-1][l-1][m-1]+5*discounts[4]*singlePrice){
                                    dp[i][j][k][l][m]=dp[i-1][j-1][k-1][l-1][m-1]+5*discounts[4]*singlePrice;
                                }
                            }
                            if(l>=1){
                                int[] temp=new int[5];
                                temp[0]=i-1;
                                temp[1]=j-1;
                                temp[2]=k-1;
                                temp[3]=l-1;
                                temp[4]=m;
                                Arrays.sort(temp);
                                if(dp[i][j][k][l][m]==0 || dp[i][j][k][l][m]>dp[temp[4]][temp[3]][temp[2]][temp[1]][temp[0]]+4*discounts[3]*singlePrice){
                                    dp[i][j][k][l][m]=dp[temp[4]][temp[3]][temp[2]][temp[1]][temp[0]]+4*discounts[3]*singlePrice;
                                }
                            }
                            if(k>=1){
                                int[] temp=new int[5];
                                temp[0]=i-1;
                                temp[1]=j-1;
                                temp[2]=k-1;
                                temp[3]=l;
                                temp[4]=m;
                                Arrays.sort(temp);
                                if(dp[i][j][k][l][m]==0 || dp[i][j][k][l][m]>dp[temp[4]][temp[3]][temp[2]][temp[1]][temp[0]]+3*discounts[2]*singlePrice){
                                    dp[i][j][k][l][m]=dp[temp[4]][temp[3]][temp[2]][temp[1]][temp[0]]+3*discounts[2]*singlePrice;
                                }
                            }
                            if(j>=1){
                                int[] temp=new int[5];
                                temp[0]=i-1;
                                temp[1]=j-1;
                                temp[2]=k;
                                temp[3]=l;
                                temp[4]=m;
                                Arrays.sort(temp);
                                if(dp[i][j][k][l][m]==0 || dp[i][j][k][l][m]>dp[temp[4]][temp[3]][temp[2]][temp[1]][temp[0]]+2*discounts[1]*singlePrice){
                                    dp[i][j][k][l][m]=dp[temp[4]][temp[3]][temp[2]][temp[1]][temp[0]]+2*discounts[1]*singlePrice;
                                }
                            }
                            if(i>=1){
                                int[] temp=new int[5];
                                temp[0]=i-1;
                                temp[1]=j;
                                temp[2]=k;
                                temp[3]=l;
                                temp[4]=m;
                                Arrays.sort(temp);
                                if(dp[i][j][k][l][m]==0 || dp[i][j][k][l][m]>dp[temp[4]][temp[3]][temp[2]][temp[1]][temp[0]]+singlePrice){
                                    dp[i][j][k][l][m]=dp[temp[4]][temp[3]][temp[2]][temp[1]][temp[0]]+singlePrice;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(dp[nums[4]][nums[3]][nums[2]][nums[1]][nums[0]]);
    }
}