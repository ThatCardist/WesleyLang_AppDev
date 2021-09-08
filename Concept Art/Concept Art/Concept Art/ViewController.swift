//
//  ViewController.swift
//  leoApp
//
//  Created by Wesley Lang on 8/31/21.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var artImg: UIImageView!
    
    @IBOutlet weak var artType: UILabel!
    @IBAction func inventions(_ sender: UIButton) {
        if(sender.tag == 1){
            artImg.image = UIImage(named: "fantasy")
            artType.text = "This is a city in the clouds"
        }else{
            artImg.image = UIImage(named: "scifi")
            artType.text = "This is a cyberpunk stye art"
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    
    
}

