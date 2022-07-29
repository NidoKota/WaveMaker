package wavemaker;

public class Main extends Object
{
    public static void main(String[] arguments)
    {
        //ビューを生成し生成ボタンのイベントを購読する
        View view = new View();
        view.generateAddListener((InputData inputData) -> 
        {
            if(inputData.waveType == WaveType.Sin)
            {            
                inputData.fo = 200;
                Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
                inputData.fo = 500;
                Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
                inputData.fo = 2000;
                Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
                inputData.fo = 5000;
                Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");

                return;
            }

            inputData.n = 3;

            inputData.fo = 200;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
            inputData.fo = 500;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
            inputData.fo = 2000;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
            inputData.fo = 5000;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");


            inputData.n = 5;

            inputData.fo = 200;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
            inputData.fo = 500;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
            inputData.fo = 2000;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
            inputData.fo = 5000;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");


            inputData.n = 7;

            inputData.fo = 200;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
            inputData.fo = 500;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
            inputData.fo = 2000;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
            inputData.fo = 5000;
            Audio.exportAudio(Audio.inputDataToAudioDataArray(inputData, 44100, 3.0, 0.95), 44100, inputData.toString() + ".wav");
        });
        
        //ウィンドウを表示する
        view.open();

        return;
    }
}
