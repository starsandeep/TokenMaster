import './App.css';
import './style.css';
import 'react-toastify/dist/ReactToastify.css';
import Digit from './Digit';
import React from 'react';
import { ToastContainer, toast } from 'react-toastify';
import axios from "axios";

export default function App() {

    //This method initializes the digits from 0-9 for user selection
    const initializeDigits =()=>{
        const intialDigits = [];
        for(let i=0; i<10; i++) {
         intialDigits.push({
            value: i,
            isHeld: false 
         });
        }
        return intialDigits;
    }

    //When the reset button is invoked clear the user digit selection 
    //and clear if there are any token already present
    const handleReset =()=>{
        setDigit(initializeDigits());
        setToken("");
    }

    //Hold the user Digit selection
    const holdDigit = (id) => {
        setDigit(oldDigits => oldDigits.map(digit => {
            return digit.value === id ?
            {...digit, isHeld: !digit.isHeld} : digit;
        }))
    }
 
    //To find out what all digits have made the selection
    const computeSelectedDigits=()=> {
        let selectedDigits = []
        digit.filter(num => num.isHeld)
            .forEach(filteredNum => selectedDigits.push(filteredNum.value));
        return selectedDigits;
    }

    //this method makes call to generate api to generate the token
    const handleSubmit =()=>{        
       if(selectedDigits.length === 0) {
            toast.error('Please select atleast 1 digit to generate the token');          
       } else {
           // toast.info('Token generation is in progress'); 
            const requestData = {
                data: selectedDigits
            };
            axios.post("http://localhost:8081/api/token/generate", requestData).then((response) => {
                 console.log(response.status, response.data.response);
                if(response.status === 200) {
                    toast.success('Token successfully genareted!'); 
                    setToken(response.data.response);
                } else {
                    toast.error('Exception occurred while generating the token! Please try after sometime.'); 
                }
                 
            }).catch(function (error) {
                console.log("Exception :" + error.message);
                toast.error('Exception occurred while generating the token! Please try after sometime.'); 
            })
        }
    }

    //This method makes a call to verify api to validate the token
     const handleValidate =()=>{
         axios.get("http://localhost:8082/api/token/validate/" + token).then((response) => {
                 console.log(response.status, response.data.response);
                if(response.status === 200) {
                    if(response.data.response === "Valid Token") {
                        toast.success('Valid Token!'); 
                    } else {
                        toast.error('Invalid Token!'); 
                    }                   
                } else {
                    toast.error('Exception occurred while validating the token! Please try after sometime.'); 
                }
                 
            }).catch(function (error) {
                console.log("Exception :" + error.message);
                toast.error('Exception occurred while generating the token! Please try after sometime.'); 
            });
      
    }

    //Holds ddigits from 0 to 9 along with user selected state
    const [digit,setDigit]=React.useState(initializeDigits())

    //Hold the generated token value
    const [token,setToken] =React.useState("")

    //Maps digit[] to digit component to populate digit buttons
    const digitElements=digit.map(num=><Digit key={num.value} value={num.value} isHeld={num.isHeld} holdDigit={()=>holdDigit(num.value)}/>)

    //Holds user selected numbers array
    const selectedDigits = computeSelectedDigits();
  
    return (  
          
        <main>
            <ToastContainer />  
            <h1 className="title">Token Master</h1>
            <h3>Selected Digits : {selectedDigits}</h3>
            <div className='digit-container'>
                {digitElements}
            </div>
             <div className='button-container'>
                <button className="generate-token" onClick={handleReset}>Reset</button>
                <button className="generate-token" onClick={handleSubmit}>Generate Token</button>
              </div>

            {token && 
                <>
                <div className="token-face">
                    <h2 className="digit-num">{token}</h2>
                </div>

                <div className='verify-token-container'>
                    <button className="generate-token" onClick={handleValidate}>Validate Token</button>                
                </div>
                </> 
            }
           
        </main >
   
    );
}