using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class audforcoll : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
	void OnCollisionEnter(Collision col){
		ball b = col.gameObject.GetComponent<ball> ();


		if (b != null) {
			audmanager.playSound ("wb");

		}
	}
}
