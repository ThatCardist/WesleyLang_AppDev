//
//  ViewController.swift
//  lab3
//
//  Created by Wesley Lang on 9/23/21.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var picLabel: UILabel!
    @IBOutlet weak var picView: UIImageView!
    @IBOutlet weak var slider: UISlider!
    @IBOutlet weak var picSelector: UISegmentedControl!
    @IBOutlet weak var capsSwitch: UISwitch!
    
    @IBAction func fontSlider(_ sender: UISlider) {
        self.picLabel.font = UIFont.systemFont(ofSize: CGFloat(slider.value))
        picLabel.adjustsFontSizeToFitWidth = true
        picLabel.minimumScaleFactor = 0.5
    }
    func changeImage(){
        if (picSelector.selectedSegmentIndex == 0){
            picLabel.text = "mountain picture"
            picView.image = UIImage(named: "Mountain")
        }else if(picSelector.selectedSegmentIndex == 1){
            picLabel.text = "clouds picture"
            picView.image = UIImage(named: "clouds")
        }else{
            picLabel.text = "city picture"
            picView.image = UIImage(named: "city")
        }
        picLabel.adjustsFontSizeToFitWidth = true
        picLabel.minimumScaleFactor = 0.5
    }
    
    @IBAction func pictureSelector(_ sender: UISegmentedControl) {
        changeImage()
        updateCaps()
    }
    @IBAction func updateCapsSwitch(_ sender: Any) {
        updateCaps()
        picLabel.adjustsFontSizeToFitWidth = true
        picLabel.minimumScaleFactor = 0.5
    }
    
    func updateCaps(){
            if capsSwitch.isOn {
                picLabel.text=picLabel.text?.uppercased()
            } else {
                picLabel.text=picLabel.text?.lowercased()
            }
        }
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

