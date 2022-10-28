using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public enum ePlayer{Left,Right}
public class player1controlscript : MonoBehaviour {

	public float speed = 15.0f;
	public bool walkleft = false,walkright = false;
	// Update is called once per frame
	void Update () {
		if (walkleft) {
			moveP1Left ();
		} else if (walkright) {
			moveP1Right ();
		}
			
	}


	public void moveP1Left(){
		transform.position += new Vector3 (0f, 0f, speed);
	}

	public void moveP1Right(){
		transform.position += new Vector3 (0f, 0f, -speed);
	}

	public void trigger_walkleft(){
		if (walkleft)
			walkleft = false;
		else
			walkleft = true;
	}

	public void trigger_walkright(){
		if (walkright)
			walkright = false;
		else
			walkright = true;
	}



	//for sound
	void OnCollisionEnter(Collision col){
		ball b = col.gameObject.GetComponent<ball> ();


		if (b != null) {
			audmanager.playSound ("pb");

		}
	}
}
