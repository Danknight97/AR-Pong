using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class cdscript : MonoBehaviour {
	
	// Use this for initialization
	void Start () {
		GetComponent<TextMesh>().text = "";

	}
	
	// Update is called once per frame
	void Update () {
			
			GetComponent<TextMesh>().text = ""+ball.countdown;



	}
}
