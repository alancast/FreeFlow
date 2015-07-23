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
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func OnPlaceTrade(sender: AnyObject) {
        /*
        var orderFields = [
        "symbol":"AMZN AMZN 151016 c 540",
        "quantity":"1",
        "bid_price":"1",
        "ask_price":"1",
        "ask_size":"1",
        "bid_size":"1",
        "price":"1",
        "side":"buy"
        ] as Dictionary<String,String>
        
        
        let request = NSMutableURLRequest(URL:url!)
        request.HTTPMethod = "POST"
*/
        let url = NSURL(string: "172.26.48.212:9999")
        
        // create the request & response
        var request = NSMutableURLRequest(URL:url!, cachePolicy: NSURLRequestCachePolicy.ReloadIgnoringLocalCacheData, timeoutInterval: 5)
        var response: NSURLResponse?
        var error: NSError?
        
        // create some JSON data and configure the request
        let jsonString = "json=[{\"str\":\"Hello\",\"num\":1},{\"str\":\"Goodbye\",\"num\":99}]"
        request.HTTPBody = jsonString.dataUsingEncoding(NSUTF8StringEncoding, allowLossyConversion: true)
        request.HTTPMethod = "POST"
        request.setValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        
        // send the request
        NSURLConnection.sendSynchronousRequest(request, returningResponse: &response, error: &error)
        
        // look at the response
        if let httpResponse = response as? NSHTTPURLResponse {
            println("HTTP response: \(httpResponse.statusCode)")
        } else {
            println("No HTTP response")
        }
        
        /*
        
        var err: NSError?
        request.HTTPBody = NSJSONSerialization.dataWithJSONObject(orderFields, options: nil, error: &err)
        
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("application/json", forHTTPHeaderField: "Accept")
        
        var task = session.dataTaskWithRequest(request, completionHandler: {data, response, error -> Void in
            println("Response: \(response)")
            var strData = NSString(data: data, encoding: NSUTF8StringEncoding)
            println("Body: \(strData)")
            var err: NSError?
            var json = NSJSONSerialization.JSONObjectWithData(data, options: .MutableLeaves, error: &err) as? NSDictionary
            
            // Did the JSONObjectWithData constructor return an error? If so, log the error to the console
            if(err != nil) {
                println(err!.localizedDescription)
                let jsonStr = NSString(data: data, encoding: NSUTF8StringEncoding)
                println("Error could not parse JSON: '\(jsonStr)'")
            }
            else {
                // The JSONObjectWithData constructor didn't return an error. But, we should still
                // check and make sure that json has a value using optional binding.
                if let parseJSON = json {
                    // Okay, the parsedJSON is here, let's get the value for 'success' out of it
                    var success = parseJSON["success"] as? Int
                    println("Succes: \(success)")
                }
                else {
                    let jsonStr = NSString(data: data, encoding: NSUTF8StringEncoding)
                    println("Error could not parse JSON: \(jsonStr)")
                }
            }
        })
        
        task.resume()
*/
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

