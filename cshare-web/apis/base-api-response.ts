export interface BaseApiResponse<T> {
  isSuccess: boolean;
  code: number;
  message: string;
  result?: T;
}
