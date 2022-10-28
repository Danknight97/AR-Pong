using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class start : MonoBehaviour {

	GameObject LoadingImage,trans;

	// Use this for initialization
	void Start () {
		LoadingImage = GameObject.Find ("loading");
		LoadingImage.SetActive (false);


	}
	
	// Update is called once per frame
	void Update () {
		
	}


	public void clicked(){
		SceneManager.LoadScene ("ARPong");
		LoadingImage.SetActive (true);
	}
		

}
