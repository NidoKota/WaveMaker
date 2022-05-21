//2054272
//松原 光汰

package wavemaker;

import java.lang.Math;

/*
* 波を生成する
*/
public class Wave extends Object
{
	/*
	* 正弦波
	*/
	public static double getSin(double t, int fo)
    {
        return Math.sin(2.0 * Math.PI * fo * t);
    }

	/*
	* 三角波
	*/
	public static double getTri(double t, int fo, int n)
    {
        double value = 0;
        for (int k = 1; k <= n; k++) value += Math.sin(k * Math.PI / 2.0) * Math.sin(k * 2.0 * Math.PI * fo * t) / Math.pow(k, 2);
        value *= 8.0 / Math.pow(Math.PI, 2);
        return value;
    }

	/*
	* 矩形波
	*/
	public static double getSqr(double t, int fo, int n)
    {
        double value = 0;
        for (int k = 1; k <= n; k++) value += Math.sin((2.0 * k - 1.0) * 2.0 * Math.PI * fo * t) / (2.0 * k - 1.0);
        value *= 4.0 / Math.PI;
        return value;
    }

	/*
	* ノコギリ波
	*/
	public static double getSaw(double t, int fo, int n)
    {
        double value = 0;
        for (int k = 1; k <= n; k++) value += Math.sin(k * 2.0 * Math.PI * fo * t) / k;
        value *= 2.0 / Math.PI;
        return value;
    }
}
