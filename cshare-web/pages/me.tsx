import { Col, Container, Pagination, Row } from "react-bootstrap";
import { CodeList } from "../components/CodeList";
import { MenuBar } from "../components/MenuBar";

export default function Me() {
  return (
    <>
      <MenuBar />
      <Container fluid="md" className="mt-4">
        <Row>
          <Col>
            <CodeList />
          </Col>
        </Row>
        <Row className="mt-4">
          <Col className="d-flex justify-content-center">
            <Pagination>
              <Pagination.First />
              <Pagination.Prev />
              <Pagination.Item key={1} active>
                1
              </Pagination.Item>
              <Pagination.Item key={2}>2</Pagination.Item>
              <Pagination.Item key={3}>3</Pagination.Item>
              <Pagination.Next />
              <Pagination.Last />
            </Pagination>
          </Col>
        </Row>
      </Container>
    </>
  );
}
