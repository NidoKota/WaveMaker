//2054272
//松原 光汰

package wavemaker;

public class Main extends Object
{
	public static void main(String[] arguments)
	{
		//ビューを生成し生成ボタンのイベントを購読する
		View view = new View();
		view.generateAddListener((InputData inputData) -> 
		{
			//入力された値をbyteの配列に変換し、wavファイルに出力する
			byte[] data = Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95);
			Audio.exportAudio(data, 44100);
		});
		
		//ウィンドウを表示する
		view.open();

		return;
	}
}
