import React, { useEffect, useRef } from "react";
import useToggle from "../../hooks/useToggle";

const Terms = ({ list, type, elementCheckHandler, checkedLists }) => {
  const [more, moreToggle, setMore] = useToggle();
  const checkBoxRef = useRef();

  const moreBtnHandler = (e) => {
    e.preventDefault();
    moreToggle();
  };

  useEffect(() => {
    checkBoxRef.current.checked = checkedLists.find(
      (element) => element.id === list.id
    )
      ? true
      : false;
  }, [checkedLists]);

  return (
    <li className={type + "__terms"}>
      <div className="terms__header">
        <input
          type="checkbox"
          onChange={(e) => elementCheckHandler(e.currentTarget.checked, list)}
          ref={checkBoxRef}
        />
        <label className="terms__terms-title">
          {list.title}
        </label>
        <button className="terms__more-btn" onClick={moreBtnHandler}>
          약관 보기
        </button>
      </div>
      <p className={"terms__content" + (!more ? "" : " active")}>
        {list.content}
      </p>
    </li>
  );
};

export default Terms;
