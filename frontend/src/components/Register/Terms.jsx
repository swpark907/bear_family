import React, { useEffect, useRef, useState } from "react";
import useToggle from "../../hooks/useToggle";

const Terms = ({ term, key, type }) => {

  const [value, onToggle, setValue] = useToggle();
  const [isChecked, setIsChecked] = useState(false);
  
  const moreBtnHandler = (e) => {
    e.preventDefault();
    onToggle();
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
      <p className={"terms__content" + (!value ? "" : " active")}>
        {term.termsContent}
      </p>
    </li>
  );
};

export default Terms;
