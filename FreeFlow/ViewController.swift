//
//  ViewController.swift
//  FreeFlow
//
//  Created by Alex Lancaster on 7/23/15.
//  Copyright (c) 2015 Alex Lancaster. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    var order_types = ["Buy", "Sell"]
    var price_types = ["Market", "Limit", "Stop Loss", "Stop Limit"]
    var ticker = "AAPL"
    var num_shares = 0
    var duration = ["Today"]
    var order_qualifier = ["None", "All or None", "Fill or Kill"]
    private var order_type_component = 0
    private var price_type_component = 1
    private var order_qualifier_component = 2
    
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if pickerView.restorationIdentifier == "order_types"{
            return order_types.count
        }
        else if pickerView.restorationIdentifier == "price_types"{
            return price_types.count
        }
        else if pickerView.restorationIdentifier == "order_qualifier"{
            return order_qualifier.count
        }
        return duration.count
    }
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String!{
        if pickerView.restorationIdentifier == "order_types"{
            return order_types[row]
        }
        else if pickerView.restorationIdentifier == "price_types"{
            return price_types[row]
        }
        else if pickerView.restorationIdentifier == "order_qualifier"{
            return order_qualifier[row]
        }
        return duration[row]
    }


}

