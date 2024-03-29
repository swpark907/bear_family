export const ID_REGEX = /^[a-z0-9-_]{6,20}$/;
export const PW_REGEX = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
export const EMAIL_REGEX = /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/
export const NICKNAME_REGEX = /^[가-힣a-zA-Z0-9]{2,8}$/