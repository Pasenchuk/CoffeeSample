using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;
using UnityEngine.UI;

public class Game : MonoBehaviour, IGameCallback
{

    public int score = 0;

    public TMP_Text scoreLabel;

    public TMP_Text scoreText;
    public GameObject farmBtn;


    public void FinishGame(string arg) {
        onExitClick();

    }

    public void onFarmClick()
    {
        score++;
        scoreText.text = "" + score;
    }

    public void onExitClick()
    {
        scoreLabel.text = "Total score:";
        farmBtn.SetActive(false);
        var activity = new AndroidJavaClass("com.unity3d.player.UnityPlayer").GetStatic<AndroidJavaObject>("currentActivity");
        activity.Call("onGameFinished", score);
    }

    // Start is called before the first frame update
    void Start()
    {
        var activity = new AndroidJavaClass("com.unity3d.player.UnityPlayer").GetStatic<AndroidJavaObject>("currentActivity");
        activity.Call("onGameStarted");
    }

    // Update is called once per frame
    void Update()
    {

    }
}
