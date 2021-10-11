//
//  ViewController.swift
//  memory
//
//  Created by Wesley Lang on 10/5/21.
//

import UIKit

class ViewController: UIViewController {
    //create card class
    class Card{
        let backgroundImg = UIImage(named: "background")
        var frontImg:UIImage
        var cardPlace: UIImageView
        var frontShowing = false
        func flipCard(){
            if(frontShowing == true){
                cardPlace.image = backgroundImg
                frontShowing = false
            }else{
                cardPlace.image = frontImg
                frontShowing = true
            }
        }
        init(front: UIImage, cardNum: UIImageView){
            frontImg = front
            cardPlace = cardNum
            cardPlace.isUserInteractionEnabled = true
        }
    }
    
    //Variables
    @IBOutlet weak var timerTime: UILabel!
    //cardVars
    @IBOutlet weak var card1: UIImageView!
    @IBOutlet weak var card2: UIImageView!
    @IBOutlet weak var card3: UIImageView!
    @IBOutlet weak var card4: UIImageView!
    @IBOutlet weak var card5: UIImageView!
    @IBOutlet weak var card6: UIImageView!
    @IBOutlet weak var card7: UIImageView!
    @IBOutlet weak var card8: UIImageView!
    @IBOutlet weak var card9: UIImageView!
    @IBOutlet weak var card10: UIImageView!
    @IBOutlet weak var card11: UIImageView!
    @IBOutlet weak var card12: UIImageView!
    
    var seconds = 0
    var matches = 0
    var cardImgArray = [UIImage(named: "triangle"), UIImage(named: "circle"),UIImage(named: "square"),UIImage(named: "spade"),UIImage(named: "heart"),UIImage(named: "triangle"), UIImage(named: "circle"),UIImage(named: "square"),UIImage(named: "spade"),UIImage(named: "heart"), UIImage(named: "hexagon"),UIImage(named: "hexagon")]
    var cardArray = [UIImageView]()
    var cardDict = [UIImageView : Card]()
    var selected1 : Card?
    
    //fill cardArray
    func resetCards(){//fix error of it not filling in all the numbers
        var done = 0
        cardDict.removeAll()
        while(done < 12){
            let place = Int.random(in: 0...12)
            if(cardDict[cardArray[place]] == nil){
                cardDict[cardArray[place]] = Card(front: cardImgArray[done]!, cardNum: cardArray[place])
                cardArray[place].image = cardDict[cardArray[place]]?.backgroundImg
                done += 1
            }
        }
    }
    
    
    var timer = Timer()
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        cardArray = [card1, card2, card3, card4, card5, card5, card6, card7, card8, card9, card10, card11, card12]
        newGame()
        //create gesture recognizers for all the cards
        let gestureRecognizer1 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card1.addGestureRecognizer(gestureRecognizer1)
        let gestureRecognizer2 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card2.addGestureRecognizer(gestureRecognizer2)
        let gestureRecognizer3 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card3.addGestureRecognizer(gestureRecognizer3)
        let gestureRecognizer4 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card4.addGestureRecognizer(gestureRecognizer4)
        let gestureRecognizer5 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card5.addGestureRecognizer(gestureRecognizer5)
        let gestureRecognizer6 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card6.addGestureRecognizer(gestureRecognizer6)
        let gestureRecognizer7 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card7.addGestureRecognizer(gestureRecognizer7)
        let gestureRecognizer8 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card8.addGestureRecognizer(gestureRecognizer8)
        let gestureRecognizer9 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card9.addGestureRecognizer(gestureRecognizer9)
        let gestureRecognizer10 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card10.addGestureRecognizer(gestureRecognizer10)
        let gestureRecognizer11 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card11.addGestureRecognizer(gestureRecognizer11)
        let gestureRecognizer12 = UITapGestureRecognizer(target: self, action: #selector(imageViewTapped))
        card12.addGestureRecognizer(gestureRecognizer12)
    }
    
    @IBAction func didTapImageView(_ sender: UITapGestureRecognizer) {
            print("did tap image view", sender)
    }
    //action funcs
    @IBAction func gameButton(_ sender: Any) {
        print("new game called")
        newGame()
    }
    
    //other funcs
    
    
    func checkMatch(_ card: Card){
        //if one card flipped do nothing
        //if two cards flipped check if match
        //if match make cards disappear and de-activate
        //if matches == 6 alert with time completed and tell to start new game
        //if not flip back over after .5 seconds
        if(selected1 == nil){
            selected1 = card
            card.cardPlace.image = card.frontImg
        }else{
            card.cardPlace.image = card.frontImg
            DispatchQueue.main.asyncAfter(deadline: .now() + 1) { [self] in
                if(selected1?.frontImg == card.frontImg && selected1?.cardPlace != card.cardPlace){
                    selected1?.cardPlace.isUserInteractionEnabled = false
                    selected1?.cardPlace.isHidden = true
                    card.cardPlace.isUserInteractionEnabled = false
                    card.cardPlace.isHidden = true
                    matches += 1
                    if (matches == 6){
                        timer.invalidate()
                        let alert=UIAlertController(title: "Congrats!", message: String(format: "You did all the matches in %d:%d. Press Start New Game to play again!", seconds/60, seconds % 60), preferredStyle: UIAlertController.Style.alert)
                        let okAction=UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil)
                        alert.addAction(okAction)
                        present(alert, animated: true, completion: nil)
                        matches = 0
                    }
                }else{
                    selected1?.cardPlace.image = selected1?.backgroundImg
                    card.cardPlace.image = card.backgroundImg
                }
            selected1 = nil
            }
        }
    }
    func newGame(){
        //show hidden cards
        //put pictures in new place
        //reset timer
        for card in cardArray{
            card.isHidden = false
            card.isUserInteractionEnabled = true
        }
        resetCards()
        seconds = 0
        timerTime.text = "0:00"
        timer = Timer.scheduledTimer(withTimeInterval: 1.0, repeats: true, block: { timer in self.updateTimer()})
    }
    
    func updateTimer(){
        seconds += 1
        if(seconds % 60 < 10){
            timerTime.text = String(format: "%d:0%d", seconds/60, seconds%60)
        }else{
            timerTime.text = String(format: "%d:%d", seconds/60, seconds%60)
        }
    }
    
    @objc func imageViewTapped(sender: UITapGestureRecognizer) {
        if let imageView = sender.view as? UIImageView {
            //use imageview in dict to call match
            checkMatch(cardDict[imageView]!)
            

        }
    }
}

