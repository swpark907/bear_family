import React, { useEffect, useState } from "react";
import { Title } from "../common";

const NickNameInput = ({ userInfo, setUserInfo }) => {
  const nickNameInputHandler = ({ target }) => {
    setUserInfo({ ...userInfo, userName: target.value });
  };

  return (
    <div className="nick-name-form">
      <Title variant={"tertiary"}>닉네임</Title>
      <div className="nick-name-input">
        <input
          className="nick-name-input__input"
          type="text"
          onChange={nickNameInputHandler}
        />
      </div>
    </div>
  );
};

export default NickNameInput;
