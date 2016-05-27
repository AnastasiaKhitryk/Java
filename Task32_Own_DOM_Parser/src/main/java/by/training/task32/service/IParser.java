package by.training.task32.service;

import by.training.task32.entity.Document;

import java.nio.file.Path;

public interface IParser {

    Document parse(Path xmlPath);

}
