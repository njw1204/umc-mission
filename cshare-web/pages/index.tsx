import { useState } from "react";
import { Col, Container, Pagination, Row } from "react-bootstrap";
import { useQuery } from "react-query";
import { useRecoilValue } from "recoil";
import { getCodes } from "../apis/code-api";
import { CodeList } from "../components/CodeList";
import { MenuBar } from "../components/MenuBar";
import { userState } from "../stores/user-store";

const PAGE_ROW_LIMIT = 30;

export default function Index() {
  const user = useRecoilValue(userState);
  const token = user.accessToken || "";
  const [page, setPage] = useState(1);
  const getCodesQuery = useQuery(
    ["getCodes", token, false, page, PAGE_ROW_LIMIT],
    () => getCodes(token, false, page, PAGE_ROW_LIMIT),
    { refetchOnWindowFocus: false }
  );
  const totalPage =
    typeof getCodesQuery.data?.result?.totalSize !== "undefined"
      ? Math.floor(
          (getCodesQuery.data?.result?.totalSize - 1) / PAGE_ROW_LIMIT
        ) + 1
      : undefined;

  return (
    <>
      <MenuBar />
      <Container fluid="md" className="mt-4">
        <Row>
          <Col>
            <CodeList data={getCodesQuery.data?.result?.items || []} />
          </Col>
        </Row>
        <Row className="mt-4">
          <Col className="d-flex justify-content-center">
            {totalPage && totalPage > 1 ? (
              <Pagination>
                {page !== 1 ? (
                  <>
                    <Pagination.First onClick={() => setPage(1)} />
                    <Pagination.Prev
                      onClick={() => setPage((page) => Math.max(1, page - 1))}
                    />
                  </>
                ) : null}
                {Array.from(Array(totalPage).keys()).map((i) => (
                  <Pagination.Item
                    key={i + 1}
                    active={i + 1 === page}
                    onClick={() => setPage(i + 1)}
                  >
                    {i + 1}
                  </Pagination.Item>
                ))}
                {page !== totalPage ? (
                  <>
                    <Pagination.Next
                      onClick={() =>
                        setPage((page) => Math.min(page + 1, totalPage))
                      }
                    />
                    <Pagination.Last onClick={() => setPage(totalPage)} />
                  </>
                ) : null}
              </Pagination>
            ) : null}
          </Col>
        </Row>
      </Container>
    </>
  );
}
