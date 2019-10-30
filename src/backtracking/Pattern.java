
package backtracking;

/**
 * 回溯算法——正则匹配
 *
 * @author <a href="mailto:your mail">Tu_ZhengSong(your mail)</a>
 */
public class Pattern {
    private boolean matched = false;

    //正则表达式
    private char[] pattern;

    //正则表达式长度
    private int plen;

    public Pattern(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }

    /**
     * @param text 文本串
     * @param tlen 长度
     * @return
     */
    public boolean match(char[] text, int tlen) {
        matched = false;
        rmatched(0, 0, text, tlen);
        return matched;
    }

    private void rmatched(int ti, int pj, char[] text, int tlen) {
        //如果已经匹配了，不用再回归
        if (matched) {
            return;
        }

        //正则表达式到结尾了
        if (pj == tlen) {
            //文本串也到结尾了
            if (ti == tlen) {
                matched = true;
                return;
            }
        }

        //* 匹配任意个字符
        if (pattern[pj] == '*') {
            for (int k = 0; k <= tlen - ti; k++) {
                rmatched(ti + k, pj + 1, text, tlen);
            }
        } else if (pattern[pj] == '?') {
            //?匹配0或1个字符
            rmatched(ti, pj + 1, text, tlen);
            rmatched(ti + 1, pj + 1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) {
            //纯字符匹配才行
            rmatched(ti + 1, pj + 1, text, tlen);
        }


    }
}
