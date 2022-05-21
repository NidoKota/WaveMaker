//2054272
//松原 光汰

package wavemaker;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.util.ArrayList;

/*
* オーディオに関する処理
*/
public class Audio 
{
    /*
    * 入力データから波を生成してbyte型の配列に変換する
    */
    public static byte[] inputDataToAudioDataArray(InputData inputData, int samplingRate, double time, double scaling)
    {
        int dataLength = (int)(samplingRate * time);

        //波形の計算し結果を入れる
        ArrayList<Double> rawDataArrayList = new ArrayList<>();
        for (int i = 0; i < dataLength; i++)
        {
            switch (inputData.waveType) {
                case Sin:
                    rawDataArrayList.add(Wave.getSin(i / (double)samplingRate, inputData.fo));
                    break;
                case Tri:
                    rawDataArrayList.add(Wave.getTri(i / (double)samplingRate, inputData.fo, inputData.n));
                    break;
                case Sqr:
                    rawDataArrayList.add(Wave.getSqr(i / (double)samplingRate, inputData.fo, inputData.n));
                    break;
                case Saw:
                    rawDataArrayList.add(Wave.getSaw(i / (double)samplingRate, inputData.fo, inputData.n));
                    break;
            }
        }

        //最大値と最小値の絶対値をとり、その値で全要素を割ることで-1~1の範囲に収める
        double max = rawDataArrayList.stream().max((d1, d2) -> d1.compareTo(d2)).get();
        double min = rawDataArrayList.stream().min((d1, d2) -> d1.compareTo(d2)).get();
        double absMax = Math.abs(min) < Math.abs(max) ? Math.abs(max) : Math.abs(min);

        //scalingの値が0~1であるか確認する
        scaling = scaling > 1.0 ? 1.0 : scaling < 0.0 ? 0.0 : scaling;

        //AudioInputStreamはbyte型しか受け付けない
        //16bit = 2 * 8bit = 2 * 1byte なので要素数を2倍する
        byte[] dataArray = new byte[dataLength * 2];
        
        //AudioInputStreamはbyte型しか受け付けない
        //char型を分解し2つのbyte型変数に格納する
        int dattaArrayIndex = 0;
        for (int i = 0; i < dataLength; i++)
        {
            //信号を-1~1の範囲に収める
            double plus1_minus1 = (rawDataArrayList.get(i) / absMax) * scaling;
            //Math.roundを使用して、より近い値でサンプリングするようにする
            short value = (short)Math.round(plus1_minus1 * 32767);

            //1byteずつ格納する
            dataArray[dattaArrayIndex] = (byte)value;
            dattaArrayIndex++;
            dataArray[dattaArrayIndex] = (byte)(value >> 8);
            dattaArrayIndex++;
        }

        return dataArray;
    }

    /*
    * byteの配列をwavファイルに出力する
    */
    public static void exportAudio(byte[] audioDataArray, int samplingRate)
    {
        AudioFormat audioFormat = 
            new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, samplingRate, 16, 1, 6, samplingRate, false);
        InputStream in = new ByteArrayInputStream(audioDataArray);
        AudioInputStream ais = new AudioInputStream(in, audioFormat, samplingRate);

        File outputFile = new File("result.wav");

		try
		{
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, outputFile);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }
}
