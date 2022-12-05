import { BaseApiResponse } from "./base-api-response";

const API_ENDPOINT = "http://localhost:9000/codes";

export interface GetCodesResult {
  items: GetCodesResultItem[];
  page: number;
  pageSize: number;
  pageSizeLimit: number;
  totalSize: number;
}

export interface GetCodesResultItem {
  codeId: number;
  userId: number;
  username: string;
  name: string;
  description: string;
  visibility: string;
  registerDateTime: string;
}

export interface GetCodeResult {
  codeId: number;
  userId: number;
  username: string;
  name: string;
  description: string;
  content: string;
  visibility: string;
  registerDateTime: string;
  updateDateTime: string;
}

export async function getCodes(
  token: string,
  me: boolean,
  page: number,
  limit: number
): Promise<BaseApiResponse<GetCodesResult>> {
  return await (
    await fetch(
      `${API_ENDPOINT}?${new URLSearchParams({
        me: String(me),
        page: String(page),
        limit: String(limit),
      })}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    )
  ).json();
}

export async function getCode(
  token: string,
  codeId: number
): Promise<BaseApiResponse<GetCodeResult>> {
  return await (
    await fetch(`${API_ENDPOINT}/${codeId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
  ).json();
}
