//2054272
//松原 光汰

package wavemaker;

/*
 入力された値
*/
public class InputData extends Object
{
    public int n;
    public int fo;
    public WaveType waveType;

    public InputData(int n, int fo, WaveType waveType)
    {
        this.n = n;
        this.fo = fo;
        this.waveType = waveType;
    }
}
