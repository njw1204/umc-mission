import { useState } from "react";
import { Accordion, Badge, Form } from "react-bootstrap";
import { useQueries } from "react-query";
import { useRecoilValue } from "recoil";
import { getCode, GetCodesResultItem } from "../apis/code-api";
import { userState } from "../stores/user-store";

enum Visibility {
  PRIVATE = "PRIVATE",
}

export interface CodeListProps {
  data: GetCodesResultItem[];
}

export function CodeList({ data }: CodeListProps) {
  const user = useRecoilValue(userState);
  const token = user.accessToken || "";
  const [selectedCodeId, setSelectedCodeId] = useState("");
  const getCodeQueries = useQueries(
    data.map((item) => ({
      queryKey: ["getCode", token, item.codeId],
      queryFn: () => getCode(token, item.codeId),
      enabled: String(item.codeId) === selectedCodeId,
      refetchOnWindowFocus: false,
    }))
  );

  return (
    <Accordion onSelect={(eventKey) => setSelectedCodeId(String(eventKey))}>
      {data.map((item) => (
        <Accordion.Item key={item.codeId} eventKey={String(item.codeId)}>
          <Accordion.Header>
            <div className="text-break me-3">
              <div>
                <span>{item.username}</span>&nbsp;/&nbsp;
                <strong className="me-2">{item.name}</strong>
                {item.visibility === Visibility.PRIVATE ? (
                  <Badge bg="secondary">Private</Badge>
                ) : null}
              </div>
              <div className="text-muted small mt-1 mb-3">
                {item.registerDateTime.split("T")[0]}
              </div>
              <div>{item.description}</div>
            </div>
          </Accordion.Header>
          <Accordion.Body>
            <Form.Group>
              <Form.Label className="text-primary">
                <strong>📑 {item.name}</strong>
              </Form.Label>
              <Form.Control
                as="textarea"
                className="font-monospace"
                rows={20}
                value={
                  getCodeQueries
                    .map((query) => query.data)
                    .find((data) => data?.result?.codeId === item.codeId)
                    ?.result?.content
                }
                readOnly
              />
            </Form.Group>
          </Accordion.Body>
        </Accordion.Item>
      ))}
    </Accordion>
  );
}
