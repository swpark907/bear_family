import React, { useEffect, useState } from "react";
import { Title } from "../common";

const NickNameInput = () => {
  const [nickName, setNickName] = useState("");
  useEffect(() => {console.log(nickName)}, [nickName])

  return (
    <div className="nick-name-form">
      <Title variant={"tertiary"}>닉네임</Title>
      <div className="nick-name-input">
        <input
          className="nick-name-input__input"
          type="text"
          onChange={(e) => {
            setNickName(e.target.value);
          }}
        />
      </div>
    </div>
  );
};

export default NickNameInput;
