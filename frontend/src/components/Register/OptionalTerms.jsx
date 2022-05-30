import React, { useState, useEffect, useCallback } from "react";
import Terms from "./Terms";
import { Title } from "../common";

const OptionalTerms = ({ opTerms, userInfo, setUserInfo }) => {
  const [checkedLists, setCheckedLists] = useState([]);

  const allCheckHandler = useCallback(
    (checked) => {
      if (checked) {
        const checkedListArray = [];
        opTerms.forEach((terms) => checkedListArray.push(terms));
        setCheckedLists(checkedListArray);
      } else {
        setCheckedLists([]);
      }
    },
    [opTerms]
  );

  const elementCheckHandler = useCallback(
    (checked, list) => {
      checked
        ? setCheckedLists([...checkedLists, list])
        : setCheckedLists(checkedLists.filter((el) => el.id !== list.id));
    },
    [checkedLists]
  );

  useEffect(() => {
    setUserInfo({
      ...userInfo,
      opTerms: checkedLists,
    });
  }, [checkedLists]);

  useEffect(() => {
    setUserInfo({
      ...userInfo,
      opTerms: checkedLists,
    });
  }, [checkedLists]);

  return (
    <ul className="terms-form__optional">
      <div className="terms-form__header">
        <Title variant="tertiary">선택 약관 전체 동의</Title>
        <input
          type="checkbox"
          onChange={(e) => allCheckHandler(e.target.checked)}
          checked={
            checkedLists.length === 0
              ? false
              : checkedLists.length === opTerms.length
              ? true
              : false
          }
        />
      </div>
      {opTerms.map((list, key) => (
        <Terms
          list={list}
          key={key}
          type="optional"
          elementCheckHandler={elementCheckHandler}
          checkedLists={checkedLists}
        />
      ))}
    </ul>
  );
};

export default OptionalTerms;
