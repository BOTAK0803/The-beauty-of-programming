// 3.5 最短摘要的形成
import java.util.*;
import static java.util.Collections.*;
class Test{
	private static void solve(String[] w, String[] q) {
        Map<String,Integer> mark = new HashMap<>();
        ArrayList<Integer> storeIndex = new ArrayList<>();
        int min = 1000000;
        int minStart = -1;
        int minEnd = -1;
        for (int i = 0; i < q.length; i++) {// 初始将每一个关键词的下标计作-1；
            mark.put(q[i],-1);
        }
        for (int i = 0; i < w.length; i++) {// 对于目标数组进行查询
            if (mark.containsKey(w[i])){ // 如果包含这个key, 更新这个key的下标；
                int k = mark.get(w[i]);
                if (storeIndex.contains(k))
                    storeIndex.remove(storeIndex.indexOf(k));
                storeIndex.add(i);
                mark.put(w[i],i);
                // 更新完下标之后判断关键词是否全部都囊括。
                if (storeIndex.size() == q.length){
                    int[] temp = getLength(storeIndex);
                    if (temp[0] < min){
                        min = temp[0];
                        minStart = temp[1];
                        minEnd = temp[2];
                    }
                    storeIndex.remove(storeIndex.indexOf(temp[1]));
                    continue;
                }
 
            }
        }
        for (int i = minStart; i <= minEnd; i++) {
            System.out.print(w[i]);
        }
    }
    private static int[] getLength(ArrayList<Integer> storeIndex) {
        sort(storeIndex);
        int start = storeIndex.get(0);
        int end = storeIndex.get(storeIndex.size()-1);
        int[] res = new int[]{end-start+1,start,end};
        return res;
    }
    public static void main(String[] args) {
        solve(new String[]{"a","b","c","d","c","e","f","g","e","h","c","g"},new String[]{"c","e","h"});
        System.out.println();
        String keyword[] = { "微软", "计算机", "亚洲" ,"交流"};
        String description[] = {
                "微软", "亚洲", "研究院", "成立", "于", "1998", "年", "，", "我们", "的", "使命",
                "是", "使", "未来", "的", "计算机", "能够", "看", "、", "听", "、", "学", "，",
                "能", "用", "自然语言", "与", "人类", "进行", "交流", "。", "在", "此", "基础", "上",
                "，", "微软", "亚洲", "研究院", "还", "将", "促进", "计算机", "在", "亚太", "地区",
                "的", "普及", "，", "改善", "亚太", "用户", "的", "计算", "体验", "。", "”"};
        solve(description,keyword);
    }

	
}