// 2.6 精确表达浮点数
class Test{
	public static void main(String[] args) {
		/**
		基本问题：在计算器中，一般使用float和double来存储小数	
		# 2.6 精确表达浮点数
## 基本问题：在计算机中，一般使用float和double来表示浮点数，但是由于计算机本身硬件的限制，不能够很准确的表示小数，为了得到精确的结果，用分数来表示小数，如果一个小数是无限小数，我们假设它是无限循环小数。
> 解法

经题目可得，我们输入的数字主要有两种
- 一种是有限小数
$$X = 0.a_1a_2...a_n$$
$$X = \frac{a_1a_2...a_n}{10^n}$$
然后再对分子分母进行最大公约数化简即可gcd
- 一种是无限循环小数
$$X = 0.a_1a_2...a_n(b_1b_2...b_m)$$
其中$(b_1b_2...b_m)$是循环体

$$X = 0.a_1a_2...a_n(b_1b_2...b_m)$$
$$10^n * X = a_1a_2...a_n.b_1b_2...b_m$$
$$10^n * X = a_1a_2...a_n + 0.b_1b_2...b_m$$
$$ X = \frac{a_1a_2...a_n + 0.b_1b_2...b_m}{10^n}$$
再看循环体
$$Y = 0.b_1b_2...b_m$$
$$10^m * Y = b_1b_2...b_m.b_1b_2...b_m$$
$$10^m *Y - Y = b_1b_2...b_m$$
$$Y = \frac{b_1b_2...b_m}{10^m-1}$$
将上式带入X的表达式中得
$$X = \frac{a_1a_2...a_n}{10^n} + \frac{b_1b_2...b_m}{10^n*(10^m - 1)}$$
		*/
		
	}
}