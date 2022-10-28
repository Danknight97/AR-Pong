﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class p2_border : MonoBehaviour {

	void OnCollisionEnter(Collision col){
		ball b = col.gameObject.GetComponent<ball> ();

		if (b != null) {
			audmanager.playSound ("ps");
			float a = b.transform.position.y;
			b.transform.position = new Vector3 (0f, a , 0f);
			scoreUI.scorePlayerRight++;
			b.stopme ();
		}
	}
	 
	 
}
