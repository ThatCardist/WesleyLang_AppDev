//
//  ViewController.swift
//  Lab4
//
//  Created by Wesley Lang on 9/29/21.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate{

    @IBOutlet weak var chickenLabel: UILabel!
    @IBOutlet weak var personLabel: UILabel!
    
    @IBOutlet weak var personStepper: UIStepper!
    @IBOutlet weak var sizeOfHome: UITextField!
    
    @IBAction func updateCounter(_ sender: UIStepper) {
        if personStepper.value == 1 {
            personLabel.text = "1 person"
        } else {
            personLabel.text=String(format: "%.0f", personStepper.value) + " people"
        }
        updateChickens()
    }
    
    func updateChickens(){
        var homeSize: Int;
        if sizeOfHome.text!.isEmpty {
            homeSize = 1
         } else {
            homeSize = Int(sizeOfHome.text!)!
         }
        if (personStepper.value < 1){
            let alert=UIAlertController(title: "Warning", message: "The number of people must be greater than 0", preferredStyle: UIAlertController.Style.alert)
            let okAction=UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: {action in
            self.personStepper.value = 1
             self.personLabel.text? = "1 person"
             self.updateChickens()
            })
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
            } //end else
        let peopleInHome = personStepper.value
        let neededChickens = Int.random(in: 0...homeSize) * Int.random(in: 0...Int(peopleInHome))
        if(neededChickens == 1){
            chickenLabel.text = "1 Chicken"
        }else{
            chickenLabel.text = "\(neededChickens) Chickens"
        }
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
            textField.resignFirstResponder()
            return true
    }
        
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateChickens()
    }

    override func viewDidLoad() {
        sizeOfHome.delegate=self
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        let tap = UITapGestureRecognizer(target: self, action: #selector(UIInputViewController.dismissKeyboard))
        view.addGestureRecognizer(tap)
        
    }
    @objc func dismissKeyboard() {
        //Causes the view (or one of its embedded text fields) to resign the first responder status.
        view.endEditing(true)
    }
}

