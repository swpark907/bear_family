import React, { useEffect, useState } from "react";
import { Title } from "../common";
import { NICKNAME_REGEX } from "../../constants/regex";

const NickNameInput = ({
  userInfo,
  setUserInfo,
  validCheck,
  setValidCheck,
}) => {
  const nickNameInputHandler = ({ target }) => {
    setUserInfo({ ...userInfo, userName: target.value });
  };

  useEffect(() => {
    const result = NICKNAME_REGEX.test(userInfo.userName);
    result
      ? setValidCheck({ ...validCheck, userName: true })
      : setValidCheck({ ...validCheck, userName: false });
  }, [userInfo.userName]);

  return (
    <div className="nick-name-form">
      <Title variant={"tertiary"}>닉네임</Title>
      <div className="nick-name-input">
        <input
          className="nick-name-input__input"
          id="userNameInput"
          type="text"
          onChange={nickNameInputHandler}
        />
        <label
          htmlFor="userNameInput"
          className={"nick-name-input__info"}
        >한글, 영문, 숫자 가능 2-8글자</label>
        <label
          htmlFor="userNameInput"
          className={
            "nick-name-input__info" +
            (userInfo.userName && !validCheck.userName ? " invalid" : " hide")
          }
        >닉네임 형식에 맞지 않습니다.</label>
      </div>
    </div>
  );
};

export default NickNameInput;
