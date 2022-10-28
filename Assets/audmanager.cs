using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class audmanager : MonoBehaviour {
	public static AudioClip paddleBounceSound,ballBounceWallsSound , playerScoresSound, winningSound;
	public AudioClip a;
	static AudioSource MusicSource;
	// Use this for initialization
	void Start () {
		paddleBounceSound = Resources.Load<AudioClip> ("fizzle");
		ballBounceWallsSound = Resources.Load<AudioClip> ("thud");
		playerScoresSound = Resources.Load<AudioClip> ("cheering");
		winningSound = Resources.Load<AudioClip> ("crackers");

		MusicSource = GetComponent<AudioSource> ();

	}
	// Update is called once per frame
	void Update () {
	}

	public static void playSound(string name){

		switch (name) {
		case "pb":
			MusicSource.clip = paddleBounceSound;
			MusicSource.Play();
			break;
		case "wb":
			MusicSource.PlayOneShot (ballBounceWallsSound,1f);
			break;
		case "ps":
			MusicSource.PlayOneShot (playerScoresSound,1f);
			break;
		case "ws":
			MusicSource.loop = true;
			MusicSource.clip =winningSound;
			MusicSource.Play ();
			break;
		}
	}
}
