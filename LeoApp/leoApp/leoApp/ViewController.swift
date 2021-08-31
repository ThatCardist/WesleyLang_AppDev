//
//  ViewController.swift
//  leoApp
//
//  Created by Wesley Lang on 8/31/21.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var artImg: UIImageView!
    
    @IBAction func inventions(_ sender: UIButton) {
        if(sender.tag == 1){
            artImg.image = UIImage(named: "invention")
        }else{
            artImg.image = UIImage(named: "drawing")
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    
    
}

