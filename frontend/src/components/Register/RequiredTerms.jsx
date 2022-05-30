import React, { useCallback } from "react";
import { useState, useEffect } from "react";
import { Title } from "../common";
import Terms from "./Terms";

const RequiredTerms = ({
  userInfo,
  setUserInfo,
  validCheck,
  setValidCheck,
  reqTerms,
  reqAllCheck,
}) => {
  const [checkedLists, setCheckedLists] = useState([]);

  const allCheckHandler = useCallback(
    (checked) => {
      if (checked) {
        const checkedListArray = [];
        reqTerms.forEach((terms) => checkedListArray.push(terms));
        setCheckedLists(checkedListArray);
      } else {
        setCheckedLists([]);
      }
    },
    [reqTerms]
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
      reqTerms: checkedLists,
    });
    checkedLists.length === reqTerms.length
      ? setValidCheck({ ...validCheck, reqTerms: true })
      : setValidCheck({ ...validCheck, reqTerms: false });
  }, [checkedLists]);

  return (
    <ul className="terms-form__required">
      <div className="terms-form__header">
        <Title variant="tertiary">필수 약관 전체 동의</Title>
        <input
          type="checkbox"
          onChange={(e) => allCheckHandler(e.target.checked)}
          checked={
            checkedLists.length === 0
              ? false
              : checkedLists.length === reqTerms.length
              ? true
              : false
          }
        />
      </div>

      {reqTerms.map((list, key) => (
        <Terms
          list={list}
          key={key}
          type="required"
          elementCheckHandler={elementCheckHandler}
          checkedLists={checkedLists}
        />
      ))}
    </ul>
  );
};

export default RequiredTerms;
