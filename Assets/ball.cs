using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ball : MonoBehaviour {
	public Vector3 initialImpulse;
	GameObject playbutton,leftp1,rightp2,leftp2,rightp1,explosion_left,explosion_right,tapText,resetButton,instText,Score3Stone
	,Score3Stone1;
	public Rigidbody rbBall;
	// Use this for initialization
	void Start () {

		rbBall = GetComponent<Rigidbody> ();

		playbutton = GameObject.Find ("Play");
		leftp1 = GameObject.Find ("right1");
		leftp2 = GameObject.Find ("right2");
		rightp1 = GameObject.Find ("left1");
		rightp2 = GameObject.Find ("left2");
		tapText = GameObject.Find ("tap");
		resetButton = GameObject.Find ("reset");
		instText = GameObject.Find ("inst");
		Score3Stone = GameObject.Find ("score3rock");
		Score3Stone1 = GameObject.Find ("score3rockdup");

		explosion_left = GameObject.Find ("exp_left");
		explosion_right = GameObject.Find ("exp_right");

		leftp1.SetActive (false);
		leftp2.SetActive (false);
		rightp1.SetActive (false);
		rightp2.SetActive (false);
		tapText.SetActive (false);
		resetButton.SetActive (false);

		explosion_left.SetActive (false);
		explosion_right.SetActive (false);

		Score3Stone.SetActive (false);
		Score3Stone1.SetActive (false);
	}
	
	// Update is called once per frame
	void Update () {
		rbBall.AddTorque (initialImpulse * initialImpulse.magnitude * Time.deltaTime);


	}

	public void play_button_clicked(){
		playbutton.SetActive (false);
		instText.SetActive (false);

		GetComponent<Rigidbody> ().AddForce (initialImpulse, ForceMode.Impulse);

		leftp1.SetActive (true);
		leftp2.SetActive (true);
		rightp1.SetActive (true);
		rightp2.SetActive (true);
	}

	public static string countdown ="";
	public static bool showCountdown = false;
	public void stopme(){
		rbBall.velocity = Vector3.zero;
		if (scoreUI.scorePlayerLeft == 5 || scoreUI.scorePlayerRight == 5) {
			if (scoreUI.scorePlayerLeft == 5) {
				won (1);
			} else
				won (2);
		}
		else
		StartCoroutine(WaitFor(3));


	}


	void won(int Player){
	//1 i left 2 is right
		audmanager.playSound ("ws");
		switch (Player) {
		case 1: 
			explosion_left.SetActive (true);
			break;
		case 2:
			explosion_right.SetActive (true);
			break;
		}

		scoreUI.scorePlayerLeft = scoreUI.scorePlayerRight = 0;

		leftp1.SetActive (false);
		leftp2.SetActive (false);
		rightp1.SetActive (false);
		rightp2.SetActive (false);

		tapText.SetActive (true);
		resetButton.SetActive (true);

	}



	IEnumerator WaitFor(int secs){
		int count = secs;
		showCountdown = true;
		while (count > 0) {
			countdown = "0"+count;
			yield return new WaitForSeconds (0.5f);
			count--;
		}
		countdown = "Go!";
		yield return new WaitForSeconds (0.5f);

		countdown = "";

		initialImpulse = -initialImpulse;
		rbBall.AddForce (initialImpulse, ForceMode.Impulse);

		if (scoreUI.scorePlayerLeft >= 3 || scoreUI.scorePlayerRight >= 3) {
			Score3Stone.SetActive (true);
			Score3Stone1.SetActive (true);
		}
			
	}
		
}
