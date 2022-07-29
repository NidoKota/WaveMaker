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

    public String toString()
    {
        return waveType.name() + "_" + String.valueOf(fo) + ((waveType == WaveType.Sin) ? "" : "_" + String.valueOf(n));
    }
}
