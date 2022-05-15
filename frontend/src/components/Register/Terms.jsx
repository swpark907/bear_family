import React, { useEffect, useRef, useState } from "react";

const Terms = ({ term, key, type }) => {
  const [isChecked, setIsChecked] = useState(false);
  const [is더보기, setIs더보기] = useState(false);

  const moreBtnHandler = (e) => {
    e.preventDefault();
    setIs더보기(!is더보기);
    console.log("clicked");
  };

  return (
    <li key={key} className={type + "__terms"}>
      <div className="terms__header">
        <input
          type="checkbox"
          onChange={() => {
            setIsChecked(!isChecked);
          }}
        />
        <label htmlFor={key} className="terms__terms-title">
          {term.termsTitle}
        </label>
        <button className="terms__more-btn" onClick={moreBtnHandler}>
          약관 보기
        </button>
      </div>
      <p className={"terms__content" + (!is더보기 ? "" : " active")}>
        {term.termsContent}
      </p>
    </li>
  );
};

export default Terms;
