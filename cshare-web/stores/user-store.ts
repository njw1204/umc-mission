import { atom, selector } from "recoil";
import { v1 } from "uuid";

const STORAGE_KEY_USER_DATA = "cshare-user-data";

export interface User {
  username?: string;
  accessToken?: string;
}

export const userState = atom<User>({
  key: `userState/${v1()}`,
  default: loadUser() || {
    username: "abcde",
    accessToken:
      "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NzAxNDM2NTYsImV4cCI6MTcwMTY3OTY1Nn0.gB0qaAWhF6jyTkhnlZAPQFdCsHXspHz9CpgOJonEnH8",
  },
  effects: [
    ({ onSet }) => {
      onSet((newValue) => {
        saveUser(newValue);
      });
    },
  ],
});

export const loginState = selector({
  key: `loginState/${v1()}`,
  get: ({ get }) => {
    return Boolean(get(userState).accessToken);
  },
});

function saveUser(user: User) {
  try {
    window.localStorage.setItem(STORAGE_KEY_USER_DATA, JSON.stringify(user));
  } catch (e) {
    console.log(e);
  }
}

function loadUser() {
  try {
    if (typeof window === "undefined") {
      return undefined;
    }

    const userData = window.localStorage.getItem(STORAGE_KEY_USER_DATA);

    if (!userData) {
      return undefined;
    }

    const user: User = JSON.parse(String(userData));

    if (!user?.username || !user?.accessToken) {
      return undefined;
    }

    return user;
  } catch (e) {
    console.log(e);
  }

  return undefined;
}
