import React from "react";

export default function Digit(props){

    const styles= {
        backgroundColor: props.isHeld ? "#59E391" : "white"
    }

    return(
        <div className="digit-face" style={styles} onClick={props.holdDigit}>
            <h2 className="digit-num">{props.value}</h2>
        </div>
    )
}
